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
  (Problem. (->SearchNode
             {:l {:m 3 :c 3 :b true} :r {:m 0 :c 0 :b false}}
             nil nil 0 0)
            {:l {:m 0 :c 0 :b false}
             :r {:m 3 :c 3 :b true}}))

(def explored (atom #{}))

(defn lifo [fringe]
  (loop [pick (first (reverse fringe))]
    (if (nil? pick)
      (println (str "Nothing to pick from fringe " fringe))
      (if  (not (contains? @explored pick))
        (do (swap! explored conj pick) pick)
        (recur (butlast fringe))))))

(defn fifo [fringe]
  (loop [pick (first fringe)]
    (if (nil? pick)
      (println (str "Nothing to pick from fringe " fringe))
      (if  (not (contains? @explored pick))
        (do (swap! explored conj pick) pick)
        (recur (butlast fringe))))))

(defn goal-test [problem maybe-goal-state]
  (= (:goal-state problem) maybe-goal-state))

(defn apply-action [node action]
  (let [to (first (keys action))
        from (if (= :r to) :l :r)
        state (:state node)]
    (SearchNode. (assoc {}
                   to {:m (+ (:m (get state to)) (:m (get action to)))
                       :c (+ (:c (get state to)) (:c (get action to)))
                       :b true}
                   from {:m (- (:m (get state from)) (:m (get action to)))
                         :c (- (:c (get state from)) (:c (get action to)))
                         :b false})
                 node
                 action
                 (inc (:pathCost node))
                 (inc (:treeDepth node)))))

(defn valid-action? [action node]
  (let [new-node (apply-action node action)
        new-state (:state new-node)]
    (and
     (not (contains? @explored new-node))
     (not= (:state (:parentNode node))
           new-state)
     (not= (first (keys action))
           (first (keys (:action node))))
     (and (>= (:m (:r new-state)) 0)
          (<= (:m (:r new-state)) 3))
     (and (>= (:m (:l new-state)) 0)
          (<= (:m (:l new-state)) 3))
     (and (>= (:c (:r new-state)) 0)
          (<= (:c (:r new-state)) 3))
     (and (>= (:c (:l new-state)) 0)
          (<= (:c (:l new-state)) 3))
     (or (= 0 (:m (:r new-state)))
         (>= (:m (:r new-state)) (:c (:r new-state))))
     (or (= 0 (:m (:l new-state)))
         (>= (:m (:l new-state)) (:c (:l new-state)))))))

(defn expand [fringe node]
  (let [valid-actions (filter #(valid-action? % node) actions)
        remaining-fringe (filter #(not (contains? @explored %)) fringe)
        new-states (map (fn [valid-action] (apply-action node valid-action)) valid-actions)]
    (flatten (conj remaining-fringe new-states))))

(defn search-tree [problem strategy]
  (loop [fringe (list (:initial-state problem))]
    (if
        (or (nil? fringe)
            (empty? fringe))
      (println "Failed to find a solution.")
      (let [node ((get strategy :function) fringe)]
        (cond
         (nil? node) nil
         (goal-test problem (:state node)) node
         :else (recur (expand fringe node)))))))
