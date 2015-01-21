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

(defrecord Problem [initial-state goal-state])
(defrecord Strategy [function])
(defrecord SearchNode [state parentNode action pathCost treeDepth])

(def actions
  (list  {:r {:m 1 :c 1}} 
         {:r {:m 0 :c 2}}
         {:r {:m 0 :c 1}}
         {:r {:m 2 :c 0}}
         {:r {:m 1 :c 0}}
         {:l {:m 1 :c 1}}
         {:l {:m 0 :c 2}}
         {:l {:m 0 :c 1}}
         {:l {:m 2 :c 0}}
         {:l {:m 1 :c 0}}))

(def problem
  (Problem. {:initial-state {:l {:m 3 :c 3 :b true}
                             :r {:m 0 :c 0 :b false}}}
            {:goal-state {:l {:m 0 :c 0 :b false}
                          :r {:m 3 :c 3 :b true}}}))

(defn lifo [fringe]
  (last fringe))

(defn fifo [fringe]
  (first fringe))

(defn goal-test [problem maybe-goal-state]
  (== (:goal-state problem)
      maybe-goal-state))

(defn apply-action [state action]
  )

(defn test-action [bank state motion]
  (let [to bank
        from (cond (= :l bank) :r :else :l)]
    (and
     (<= (+ (:m (to state))   (:m motion)) 3)
     (<= (+ (:c (to state))   (:c motion)) 3)
     (>  (- (:m (from state)) (:m motion)) 0)
     (>  (- (:c (from state)) (:c motion)) 0)
     (and
      (> (+ (:m  (to state))   (:m motion)) 0)
      (<= (+ (:c (from state)) (:c motion))
          (+ (:m (to state)    (:m motion)))))))) 

(defn valid-action? [action node]
  (let [state (:state node)]
    (test-action
     (first (keys action))
     state
     (first (vals action)))))
    
(defn expand [fringe node]
  (conj
   (map
    #(SearchNode. (:state node) node % (inc (:pathCost node)) (inc (:treeDepth node)))
    (filter
     #(valid-action? % node)
     actions))
   fringe))

(defn search-tree [problem strategy]
  (loop [fringe (:initial-state problem)]
    (if
        (or (nil? fringe)
            (empty? fringe))
      nil
      (let [node ((:fuction strategy) fringe)]
        (cond
         (nil? node) nil
         (goal-test problem (:state node)) node
         :else (recur (expand fringe node)))))))
