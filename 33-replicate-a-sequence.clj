(fn [lst tms]
   (reduce (fn [coll ele]
             (concat coll (repeat tms ele))) [] lst))