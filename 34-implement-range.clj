(fn [incl excl]
   (loop [resu '()
          right excl]
     (if (>= incl right)
       resu
       (recur (conj resu (dec right)) (dec right)))))