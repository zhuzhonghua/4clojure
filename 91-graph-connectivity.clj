(defn mm[graph]
  (let [vertices (set (apply concat graph))]
    (letfn [(travel [vertex past-vertex]
              ;;(println vertex)
              (let [adjacent-vertex (filter #(not (past-vertex %))
                                            (apply concat
                                                   (filter #(some #{vertex} %)
                                                           graph)))]
                (do 
                  (if (seq adjacent-vertex)
                    (some true?
                          (map #(travel % (concat % past-vertex))
                               adjacent-vertex))
                    (= past-vertex vertices)))))]
      (some #(travel % #{%}) vertices))))

(println (mm #{[:a :a]}))
