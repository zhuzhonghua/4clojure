(fn f
  ([tree] (and (= 3 (count tree))
               (not (sequential? (first tree)))
               (f (second tree) (nth tree 2))))
  ([coll1 coll2] (or (and (nil? coll1)
                          (nil? coll2))
                     (and (sequential? coll1)
                          (sequential? coll2)
                          (= (first coll1) (first coll2))
                          (let [l1 (second coll1)
                                r1 (nth coll1 2)
                                l2 (second coll2)
                                r2 (nth coll2 2)]
                            (and (f l1 r2)
                                 (f r1 l2)))))))