(fn f [coll]
   (and (= 3 (count coll))
        ((complement sequential?) (first coll))
        (let [secnd (second coll)
              third (nth coll 2)]
          (and (if (sequential? secnd)
                 (f secnd)
                 ((complement false?) secnd))
               (if (sequential? third)
                 (f third)
                 ((complement false?) third))))))