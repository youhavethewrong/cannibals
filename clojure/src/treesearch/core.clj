(ns treesearch.core)

;; On one bank of a river are three missionaries (black triangles) and
;; three cannibals (red circles). There is one boat available that can
;; hold up to two people and that they would like to use to cross the
;; river. If the cannibals ever outnumber the missionaries on either
;; of the river’s banks, the missionaries will get eaten. How can the
;; boat be used to safely carry all the missionaries and cannibals
;; across the river?


;; Try to implement the general search algorithm just described. You
;; can use LIFO and FIFO as queuing strategies to determine the order
;; in which nodes are explored. These two strategies are known as
;; depth-first and breadth-first search respectively. Be careful,
;; depth-first search may descend down infinite branches, so best
;; implement a depth cut-off. Then, extend your implementation with a
;; hash table that stores all the nodes found so far. Print out a
;; trace of the states the algorithm finds (in the order they are
;; discovered) and see how much of the search spaceeach algorithm
;; explores.


(defn binary-search1
  [coll ^long coll-size  target]
  (let [cnt (dec coll-size)]
    (loop [low-idx 0 high-idx cnt]
      (if (> low-idx high-idx)
        nil
        (let [mid-idx (quot (+ low-idx high-idx) 2) mid-val (coll mid-idx)]
          (cond
           (= mid-val target) mid-idx
           (< mid-val target) (recur (inc mid-idx) high-idx)
           (> mid-val target) (recur low-idx (dec mid-idx))))))))

(defn binary-search2
  "Finds earliest occurrence of x in xs (a vector) using binary search."
  ([xs x]
     (loop [l 0 h (unchecked-dec (count xs))]
       (if (<= h (inc l))
         (cond
          (== x (xs l)) l
          (== x (xs h)) h
          :else nil)
         (let [m (unchecked-add l (bit-shift-right (unchecked-subtract h l) 1))]
           (if (< (xs m) x)
             (recur (unchecked-inc m) h)
             (recur l m)))))))

(def explored (atom {}))

(defn strategy
  "Pick a node to try next."
  [fringe]
  {:state (next fringe)})

(defn goal-test [problem maybe-goal-state]
  (== (:goal-state problem)
      maybe-goal-state))

(defn applicable [action node])

(def problem
  {:initial-state {:left {:m 3 :c 3 :b true}
                   :right {:m 0 :c 0 :b false}}}
  {:goal-state {:left {:m 0 :c 0 :b false}
                :right {:m 3 :c 3 :b true}}}
  {:applicable applicable}
  {:actions [:2m :1m1c :2c]})


(defn search-tree [problem strategy]
  ;; what do i need to reset every loop?  fringe and node?
  (loop [fringe (:initial-state problem)]
    (if (empty? fringe)
      nil
      (let [node (strategy fringe)]
        (if (goal-test problem (:state node))
          
          )
        )))) 
