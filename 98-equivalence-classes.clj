(fn [f coll-set]
   (set (map #(-> %
                  second
                  set) (group-by #(f %) coll-set))))