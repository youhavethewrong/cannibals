(ns treesearch.core)

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
  (Problem. (->SearchNode {:l {:m 3 :c 3 :b true} :r {:m 0 :c 0 :b false}}
                          nil
                          nil
                          0
                          0)
            {:l {:m 0 :c 0 :b false}
             :r {:m 3 :c 3 :b true}}))

(def explored (atom #{}))

(defn take-and-update [pick]
  (println (str "Thinking about taking " pick))
  (when (not (nil? pick))
    
    pick))

(defn lifo [fringe]
  (let [pick (last (filter #(not (contains? @explored (:state %))) fringe))]
    (when (not (nil? pick))
      (swap! explored conj (:state pick))
      pick)))

(defn fifo [fringe]
  (first fringe))

(defn goal-test [problem maybe-goal-state]
  (= (:goal-state problem)
      maybe-goal-state))

(defn apply-action [state action]
  (let [to (first (keys action))
        from (if (= :r to) :l :r)]
    (assoc {}
      to {:m (+ (:m (get state to)) (:m (get action to)))
          :c (+ (:c (get state to)) (:c (get action to)))
          :b (not (:b (get state to)))}
      from {:m (- (:m (get state from)) (:m (get action to)))
            :c (- (:c (get state from)) (:c (get action to)))
            :b (not (:b (get state from)))})))

(defn valid-action? [action node]
  (let [new-state (apply-action (:state node) action)]
    (and
     (not= (first (vals action))
           (first (vals (:action node))))
     (or (and (:b (:r new-state))
              (not (:b (:l new-state))))
         (and (:b (:l new-state))
              (not (:b (:r new-state)))))
     (<= (:m (:r new-state)) 3)
     (<= (:m (:l new-state)) 3)
     (<= (:c (:r new-state)) 3)
     (<= (:c (:l new-state)) 3)
     (>= (:m (:r new-state)) 0)
     (>= (:m (:l new-state)) 0)
     (>= (:c (:r new-state)) 0)
     (>= (:c (:l new-state)) 0)
     (or (= 0 (:m (:r new-state)))
         (>= (:m (:r new-state)) (:c (:r new-state))))
     (or (= 0 (:m (:l new-state)))
         (>= (:m (:l new-state)) (:c (:l new-state)))))))

(defn expand [fringe node]
  (flatten
   (conj
    (map
     (fn [action]
       (SearchNode. (apply-action (:state node) action)
                    node
                    action
                    (inc (:pathCost node))
                    (inc (:treeDepth node))))
     (filter
      #(valid-action? % node)
      actions))
    fringe)))

(defn search-tree [problem strategy]
  (loop [fringe (list (:initial-state problem))]
    (if
        (or (nil? fringe)
            (empty? fringe))
      (println "What, me worry?")
      (let [node ((get strategy :function) fringe)]
        (cond
         (nil? node) nil
         (goal-test problem (:state node)) node
         :else (recur (expand fringe node)))))))
