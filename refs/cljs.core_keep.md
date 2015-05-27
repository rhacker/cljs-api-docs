## <img width="48px" valign="middle" src="http://i.imgur.com/Hi20huC.png"> cljs.core/keep

 <table border="1">
<tr>
<td>function</td>
<td><a href="https://github.com/cljsinfo/api-refs/tree/0.0-927"><img valign="middle" alt="[+] 0.0-927" src="https://img.shields.io/badge/+-0.0--927-lightgrey.svg"></a> </td>
<td>
[<img height="24px" valign="middle" src="http://i.imgur.com/1GjPKvB.png"> <samp>clojure.core/keep</samp>](http://clojure.github.io/clojure/branch-master/clojure.core-api.html#clojure.core/keep)
</td>
</tr>
</table>

 <samp>
(__keep__ f coll)<br>
</samp>

```
Returns a lazy sequence of the non-nil results of (f item). Note,
this means false return values will be included.  f must be free of
side-effects.
```

---

 <pre>
clojurescript @ r1211
└── src
    └── cljs
        └── cljs
            └── <ins>[core.cljs:1945-1955](https://github.com/clojure/clojurescript/blob/r1211/src/cljs/cljs/core.cljs#L1945-L1955)</ins>
</pre>

```clj
(defn keep
  ([f coll]
   (lazy-seq
    (when-let [s (seq coll)]
      (let [x (f (first s))]
        (if (nil? x)
          (keep f (rest s))
          (cons x (keep f (rest s)))))))))
```


---

```clj
{:ns "cljs.core",
 :name "keep",
 :signature ["[f coll]"],
 :history [["+" "0.0-927"]],
 :type "function",
 :full-name-encode "cljs.core_keep",
 :source {:code "(defn keep\n  ([f coll]\n   (lazy-seq\n    (when-let [s (seq coll)]\n      (let [x (f (first s))]\n        (if (nil? x)\n          (keep f (rest s))\n          (cons x (keep f (rest s)))))))))",
          :filename "clojurescript/src/cljs/cljs/core.cljs",
          :lines [1945 1955],
          :link "https://github.com/clojure/clojurescript/blob/r1211/src/cljs/cljs/core.cljs#L1945-L1955"},
 :full-name "cljs.core/keep",
 :clj-symbol "clojure.core/keep",
 :docstring "Returns a lazy sequence of the non-nil results of (f item). Note,\nthis means false return values will be included.  f must be free of\nside-effects."}

```