(fn f [coll]
   (if (and (sequential? coll)
            (sequential? (first coll)))
     (let [resu (concat [] (f (first coll)))]
       (if (not (empty? (rest coll)))
         (concat resu (f (rest coll)))
         resu))
     [coll]))