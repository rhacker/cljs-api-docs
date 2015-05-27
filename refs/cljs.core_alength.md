## <img width="48px" valign="middle" src="http://i.imgur.com/Hi20huC.png"> cljs.core/alength

 <table border="1">
<tr>
<td>function</td>
<td><a href="https://github.com/cljsinfo/api-refs/tree/0.0-927"><img valign="middle" alt="[+] 0.0-927" src="https://img.shields.io/badge/+-0.0--927-lightgrey.svg"></a> </td>
<td>
[<img height="24px" valign="middle" src="http://i.imgur.com/1GjPKvB.png"> <samp>clojure.core/alength</samp>](http://clojure.github.io/clojure/branch-master/clojure.core-api.html#clojure.core/alength)
</td>
</tr>
</table>

 <samp>
(__alength__ array)<br>
</samp>

```
Returns the length of the array. Works on arrays of all types.
```

---

 <pre>
clojurescript @ r1211
└── src
    └── cljs
        └── cljs
            └── <ins>[core.cljs:101-104](https://github.com/clojure/clojurescript/blob/r1211/src/cljs/cljs/core.cljs#L101-L104)</ins>
</pre>

```clj
(defn alength
  [array]
  (.-length array))
```


---

 <pre>
clojurescript @ r1211
└── src
    └── clj
        └── cljs
            └── <ins>[core.clj:804-805](https://github.com/clojure/clojurescript/blob/r1211/src/clj/cljs/core.clj#L804-L805)</ins>
</pre>

```clj
(defmacro alength [a]
  (list 'js* "~{}.length" a))
```

---

```clj
{:ns "cljs.core",
 :name "alength",
 :signature ["[array]"],
 :shadowed-sources ({:code "(defmacro alength [a]\n  (list 'js* \"~{}.length\" a))",
                     :filename "clojurescript/src/clj/cljs/core.clj",
                     :lines [804 805],
                     :link "https://github.com/clojure/clojurescript/blob/r1211/src/clj/cljs/core.clj#L804-L805"}),
 :history [["+" "0.0-927"]],
 :type "function",
 :full-name-encode "cljs.core_alength",
 :source {:code "(defn alength\n  [array]\n  (.-length array))",
          :filename "clojurescript/src/cljs/cljs/core.cljs",
          :lines [101 104],
          :link "https://github.com/clojure/clojurescript/blob/r1211/src/cljs/cljs/core.cljs#L101-L104"},
 :full-name "cljs.core/alength",
 :clj-symbol "clojure.core/alength",
 :docstring "Returns the length of the array. Works on arrays of all types."}

```