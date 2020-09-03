(fn [n1 n2]
  (loop [mul (* n1 n2)
         resu []]
    (if (= mul 0)
      resu
      (recur (int (/ mul 10)) (cons (mod mul 10) resu)))))