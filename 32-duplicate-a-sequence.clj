(fn [arg]
  (reverse (reduce (fn [col el]
                    (conj col el el)) '() arg)))