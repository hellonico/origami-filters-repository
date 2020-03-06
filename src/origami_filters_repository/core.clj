(ns origami-filters-repository.core)

(defn handler [request]
	(let [query (or (:query-string request) "")  file (second (clojure.string/split query #"=")) ] 
	(if file
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (slurp file)}
   {:status 200
   :headers {"Content-Type" "text/html"}
   :body 
   (apply str 
   (map #(str "<a href=\"/?file=" % "\"/>" % "</a>") 
   (filter #(clojure.string/includes? % ".edn") (file-seq (clojure.java.io/file"."))))) }
	)))