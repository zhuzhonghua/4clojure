(fn [coll]
  (reduce (fn [resu item]
            (assoc resu item (inc (get resu item 0))))
          {}
          coll))