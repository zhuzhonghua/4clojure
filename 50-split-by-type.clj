(fn [coll]
   (vals (reduce (fn [resu item]
                   (let [t (type item)
                         v (get resu t)]
                     (if v
                       (assoc resu t (conj v item))
                       (assoc resu t (vector item)))))
                 {}
                 coll)))