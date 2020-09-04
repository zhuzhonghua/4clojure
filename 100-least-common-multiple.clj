(fn [& arg]
  (/ (apply * arg)
     (reduce (fn [a b]
               (if (= (mod a b) 0)
                 b
                 (recur b (mod a b))))
             arg)))