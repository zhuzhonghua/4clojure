(fn [n]
   (loop [resu 1
          in n]
     (if (> in 1)
       (recur (* in resu)
             (- in 1))
       resu)))