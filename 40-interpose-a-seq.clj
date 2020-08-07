(fn [itm coll]
   (loop [resu []
          col coll]
     (if (empty? (rest col))
       (conj resu (first col))
       (recur (conj resu (first col) itm)
              (rest col)))))