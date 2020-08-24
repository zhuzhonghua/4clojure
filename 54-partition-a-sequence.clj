(fn [n coll]
  (:lst
    (reduce (fn [resu item]
             (let [lst (:lst resu)
                   cur (conj (:cur resu) item)]
               (if (= n (count cur))
                 (assoc resu :lst (conj lst cur)
                             :cur [])
                 (assoc resu :cur cur))))
           {:lst [] :cur []}
           coll)))