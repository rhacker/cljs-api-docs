## <img width="48px" valign="middle" src="http://i.imgur.com/Hi20huC.png"> cljs.core/clj->js

 <table border="1">
<tr>
<td>function</td>
<td><a href="https://github.com/cljsinfo/api-refs/tree/0.0-1552"><img valign="middle" alt="[+] 0.0-1552" src="https://img.shields.io/badge/+-0.0--1552-lightgrey.svg"></a> </td>
</tr>
</table>

 <samp>
(__clj->js__ x)<br>
</samp>

```
Recursively transforms ClojureScript values to JavaScript.
sets/vectors/lists become Arrays, Keywords and Symbol become Strings,
Maps become Objects. Arbitrary keys are encoded to by key->js.
```

---

 <pre>
clojurescript @ r1586
└── src
    └── cljs
        └── cljs
            └── <ins>[core.cljs:6928-6933](https://github.com/clojure/clojurescript/blob/r1586/src/cljs/cljs/core.cljs#L6928-L6933)</ins>
</pre>

```clj
(defn clj->js
   [x]
   (-clj->js x))
```


---

```clj
{:full-name "cljs.core/clj->js",
 :ns "cljs.core",
 :name "clj->js",
 :docstring "Recursively transforms ClojureScript values to JavaScript.\nsets/vectors/lists become Arrays, Keywords and Symbol become Strings,\nMaps become Objects. Arbitrary keys are encoded to by key->js.",
 :type "function",
 :signature ["[x]"],
 :source {:code "(defn clj->js\n   [x]\n   (-clj->js x))",
          :filename "clojurescript/src/cljs/cljs/core.cljs",
          :lines [6928 6933],
          :link "https://github.com/clojure/clojurescript/blob/r1586/src/cljs/cljs/core.cljs#L6928-L6933"},
 :full-name-encode "cljs.core_clj-_GT_js",
 :history [["+" "0.0-1552"]]}

```