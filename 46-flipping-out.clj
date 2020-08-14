(fn [f]
  (fn [arg1 arg2]
    (apply f (list arg2 arg1))))