## <img width="48px" valign="middle" src="http://i.imgur.com/Hi20huC.png"> cljs.core/lazy-seq

 <table border="1">
<tr>
<td>macro</td>
<td><a href="https://github.com/cljsinfo/api-refs/tree/0.0-927"><img valign="middle" alt="[+] 0.0-927" src="https://img.shields.io/badge/+-0.0--927-lightgrey.svg"></a> </td>
<td>
[<img height="24px" valign="middle" src="http://i.imgur.com/1GjPKvB.png"> <samp>clojure.core/lazy-seq</samp>](http://clojure.github.io/clojure/branch-master/clojure.core-api.html#clojure.core/lazy-seq)
</td>
</tr>
</table>

 <samp>
(__lazy-seq__ & body)<br>
</samp>

```
(no docstring)
```

---

 <pre>
clojurescript @ r1211
└── src
    └── clj
        └── cljs
            └── <ins>[core.clj:561-562](https://github.com/clojure/clojurescript/blob/r1211/src/clj/cljs/core.clj#L561-L562)</ins>
</pre>

```clj
(defmacro lazy-seq [& body]
  `(new cljs.core.LazySeq nil false (fn [] ~@body)))
```


---

```clj
{:full-name "cljs.core/lazy-seq",
 :ns "cljs.core",
 :name "lazy-seq",
 :type "macro",
 :signature ["[& body]"],
 :source {:code "(defmacro lazy-seq [& body]\n  `(new cljs.core.LazySeq nil false (fn [] ~@body)))",
          :filename "clojurescript/src/clj/cljs/core.clj",
          :lines [561 562],
          :link "https://github.com/clojure/clojurescript/blob/r1211/src/clj/cljs/core.clj#L561-L562"},
 :full-name-encode "cljs.core_lazy-seq",
 :clj-symbol "clojure.core/lazy-seq",
 :history [["+" "0.0-927"]]}

```