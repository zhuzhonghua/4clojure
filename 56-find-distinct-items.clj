(fn [coll]
  (:lst (reduce (fn [resu item]
                  (let [lst-set (:lst-set resu)
                        lst (:lst resu)]
                    (if (lst-set item)
                      resu
                      (assoc resu :lst (conj lst item)
                                  :lst-set (conj lst-set item)))))
                {:lst     []
                 :lst-set #{}}
                coll)))