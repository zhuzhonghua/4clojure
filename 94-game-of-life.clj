(fn [cc]
   (let [width (count cc)
         height (count (nth cc 0))
         get-neighbours (fn [i j]
                          (map #(-> cc
                                    (nth (nth % 0))
                                    (nth (nth % 1)))
                               (filter (fn [item]
                                         (let [ii (nth item 0)
                                               jj (nth item 1)]
                                           (and (>= ii 0)
                                                (< ii width)
                                                (>= jj 0)
                                                (< jj height))))
                                       [[(dec i) j]
                                        [(inc i) j]
                                        [i (dec j)]
                                        [i (inc j)]
                                        [(dec i) (inc j)]
                                        [(inc i) (inc j)]
                                        [(dec i) (dec j)]
                                        [(inc i) (dec j)]])))]
     (map-indexed (fn [i icc]
                    (reduce str (map-indexed (fn [j jcc]
                                               (let [neighbours (get-neighbours i j)
                                                     live (count (filter #(= % \#) neighbours))
                                                     dead (count (filter #(= % \space) neighbours))]
                                                 (cond
                                                   (and (= jcc \#) (< live 2))
                                                   \space
                                                   (and (= jcc \#) (or (= live 2) (= live 3)))
                                                   \#
                                                   (and (= jcc \#) (> live 3))
                                                   \space
                                                   (and (= jcc \space) (= live 3))
                                                   \#
                                                   :else jcc)))
                                             icc)))
                 cc)))