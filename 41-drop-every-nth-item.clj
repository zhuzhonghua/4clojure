(fn [coll th]
   (:resu
     (reduce (fn [val itm]
               (let [idx (:idx val)
                     resu (:resu val)]
                 (if (== th (inc idx))
                   (assoc val :idx 0)
                   (assoc val :idx (inc idx)
                              :resu (conj resu itm)))))
             {:idx 0 :resu []}
             coll)))