(fn [n]
  (loop [i 1
         resu [1]]
    (if (= i n)
      resu
      (recur (inc i) (concat [1] (map #(+ %1 %2) resu (rest resu)) [1])))))