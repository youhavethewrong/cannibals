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
    (println (str "State :l " (:l (:state node)) " :r " (:r (:state node)) " via " (:action node) " at depth " (:treeDepth node)))))

(deftest exerciseStrategy
  (testing "should exercise a few scenarios for picking nodes from the fringe"
    (is
     (= {:l {:m 0 :c 1 :b false} :r {:m 3 :c 2 :b true}}
        (let [strat (->Strategy fifo)]
          (:state ((:function strat) tinyfringe)))))
    (swap! explored conj {:r {:m 2 :c 2 :b true} :l {:m 1 :c 1 :b false}})
    (is
     (= {:l {:m 0 :c 1 :b false} :r {:m 3 :c 2 :b true}}
        (let [strat (->Strategy lifo)]
          (:state ((:function strat) tinyfringe)))))))

(deftest exerciseExpand
  (testing "should expand the available nodes from the initial state"
    (let [fringe (expand [] initial-node)]
      (is
       (= 3
          (count fringe))))
    (let [fringe (expand [] (first tinyfringe))]
      (is
       (= 3
          (count fringe))))))

(deftest game
  (testing "should plan the cannibals and missionaries problem"
    (let [strat (->Strategy lifo)
          solutionNode (search-tree problem strat)]
      (pretty-print solutionNode)
      (is
       (= 11
          (:pathCost solutionNode)))))) 
