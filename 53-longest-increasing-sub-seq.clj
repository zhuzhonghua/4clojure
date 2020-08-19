(fn [coll]
   (:max-coll
     (reduce (fn [resu item]
               (let [max-seq (:max-coll resu)
                     cur-seq (:cur-coll resu)]
                 (cond (nil? cur-seq) (assoc resu :cur-coll [item]
                                                  :max-coll [])
                       (= (+ 1 (last cur-seq)) item) (let [cur-seq (conj cur-seq item)]
                                                       (if (or (nil? max-seq)
                                                               (> (count cur-seq)
                                                                  (count max-seq)))
                                                         (assoc resu :max-coll cur-seq
                                                                     :cur-coll cur-seq)
                                                         (assoc resu :cur-coll cur-seq)))
                       :else (assoc resu :cur-coll [item]))))
             {}
             coll)))