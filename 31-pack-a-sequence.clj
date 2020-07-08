(fn [arg]
  (reverse (reduce (fn [col el]
                     (if (and (not-empty (first col))
                              (= (first (first col)) el))
                       (conj (rest col) (conj (first col) el))
                       (conj col (list el)))) '() arg)))