(defn mm[graph]
  (let [vertices (set (apply concat graph))]
    (letfn [(travel [vertex past-vertex]
              ;;(println vertex)
              ;;(println past-vertex)
              (let [adjacent-vertex (filter #(not (past-vertex %))
                                            (apply concat
                                                   (filter #(some #{vertex} %)
                                                           graph)))]
                ;;(println (seq adjacent-vertex))
                (do 
                  (if (seq adjacent-vertex)                    
                    (some #(travel % (conj past-vertex %))
                          adjacent-vertex)
                    (= past-vertex vertices)))))]
      (= true (some #(travel % #{%}) vertices)))))

;;(println (mm #{[:a :a]}))
;;(println (mm #{[:a :b]}))
;;(println (mm #{[1 2] [2 3] [3 1]
;;               [4 5] [5 6] [6 4]}))
;;(println (mm #{[1 2] [2 3] [3 1]
;;               [4 5] [5 6] [6 4] [3 4]}))
;;(println (mm #{[:a :b] [:b :c] [:c :d]
;;               [:x :y] [:d :a] [:b :e]}))
(println (mm #{[:a :b] [:b :c] [:c :d]
               [:x :y] [:d :a] [:b :e] [:x :a]}))
;;(mm #{[:a :b]})
;;(println (map #(= 1 %) '(1 2 3 4)))
