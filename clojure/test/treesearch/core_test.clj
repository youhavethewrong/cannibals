(ns treesearch.core-test
  (:require [clojure.test :refer :all]
            [treesearch.core :refer :all]))

(def initial-node
  (->SearchNode {:l {:m 3 :c 3 :b true} :r {:m 0 :c 0 :b false}}
                nil
                nil
                0
                0))

(def tinyfringe
  [(->SearchNode {:l {:m 0 :c 1 :b false} :r {:m 3 :c 2 :b true}}
                nil
                {:r {:m 1 :c 1}}
                1
                1)
   (->SearchNode {:l {:m 1 :c 1 :b false} :r {:m 2 :c 2 :b true}}
                nil
                {:r {:m 0 :c 1}}
                1
                1)])

(defn pretty-print [node]
  (when (not (nil? node))
    (pretty-print (:parentNode node))
    (println (str "State :l " (:l (:state node))
                  " :r " (:r (:state node))
                  " via " (:action node)
                  " at depth " (:treeDepth node)))))

(deftest exerciseApplyAction
  (testing "should apply an action"
    (is
     (= {:l {:m 3 :c 2 :b false} :r {:m 0 :c 1 :b true}}
        (:state (apply-action initial-node {:r {:m 0 :c 1}}))))))

(deftest exerciseStrategy
  (testing "should exercise a few scenarios for picking nodes from the fringe"
    (reset! explored #{})
    (is
     (= {:l {:m 0 :c 1 :b false} :r {:m 3 :c 2 :b true}}
        (let [strat (->Strategy fifo)]
          (:state ((:function strat) tinyfringe)))))
    (reset! explored #{})
    (is
     (= {:r {:m 2, :c 2, :b true}, :l {:m 1, :c 1, :b false}}
        (let [strat (->Strategy lifo)]
          (:state ((:function strat) tinyfringe)))))))

(deftest exerciseExpand
  (testing "should expand the available nodes from the initial state"
    (reset! explored #{})
    (let [fringe (expand (list initial-node) initial-node)
          strat (->Strategy lifo)
          step (expand fringe ((get strat :function) fringe))
          twostep (expand step ((get strat :function) step))]
      (is
       (not (nil? fringe)))
      (is
       (= 4
          (count fringe)))
      (is
       (= 3
          (count step)))
      (is
       (= 2
          (count twostep))))
    (reset! explored #{})
    (let [fringe (expand (list (first tinyfringe)) (first tinyfringe))]
      (is
       (= 3
          (count fringe))))))

(deftest game
  (testing "should plan the cannibals and missionaries problem"
    (reset! explored #{})
    (let [strat (->Strategy fifo)
          solutionNode (search-tree problem strat)]
      (pretty-print solutionNode)
      (is
       (= 11
          (:pathCost solutionNode)))))) 
