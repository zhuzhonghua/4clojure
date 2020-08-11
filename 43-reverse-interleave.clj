(fn [coll n]
   (loop [i 0
          icoll coll
          resu []]
     (if (< i n)
       (recur (+ i 1)
              (rest icoll)
              (conj resu (take-nth n icoll)))
       resu)))