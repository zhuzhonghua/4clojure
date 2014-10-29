(defn mm[graph]
  (letfn [(step [ver path]
            (let [next-edge (filter
                             #(nil? (some #{%} path))
                             (filter #(some #{ver} %) graph))]
              (if (= (count path) (count graph))
                true
                (if (seq next-edge)
                  (true?
                   (some
                    true?
                    (map
                     #(step
                       (first (filter (fn [vertex] (not= ver vertex)) %))
                       (cons % path))
                     next-edge)))
                  false))))]
    (step (first (flatten graph)) [])))

(println (mm [[:a :b]]))
(println (mm [[:a :a] [:b :b]]))
