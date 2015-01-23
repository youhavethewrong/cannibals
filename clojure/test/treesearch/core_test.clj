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

(deftest exerciseStrategy
  (testing "should exercise a few scenarios for picking nodes from the fringe"
    (is
     (= {:l {:m 0 :c 1 :b false} :r {:m 3 :c 2 :b true}}
        (let [strat (->Strategy fifo)]
          (:state ((:function strat) tinyfringe))))))
    (is
     (= {:l {:m 1 :c 1 :b false} :r {:m 2 :c 2 :b true}}
        (let [strat (->Strategy lifo)]
          (:state ((:function strat) tinyfringe))))))

(deftest exerciseExpand
  (testing "should expand the available nodes from the initial state"
    (let [fringe (expand [] initial-node)]
      (is
       (= 4
          (count fringe))))
    (let [fringe (expand [] (first tinyfringe))]
      (is
       (= 5
          (count fringe))))))

;; (deftest game
;;   (testing "should plan the cannibals and missionaries problem"
;;     (is
;;      (= 11
;;         (count (let [strat (->Strategy fifo)]
;;                  (search-tree
;;                   problem
;;                   strat)))))))
