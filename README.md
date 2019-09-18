# Springでgraphqlのサーバーを立ててみる

## 著者と本のCRUD(UDないけど)

* http://localhost:8080でアクセス
* graphqlサンプル
    * query 
    ```
    query {
      listAuthor{
        id
        name
        books {
          id
          name
        }
      }
    }
    ```
    ```
    query {
      listBook{
        id
        name
        author {
          id
          name
        }
      }
    }
    ```

    * mutation
    ```
    mutation {
      registAuthor(name: "太郎") {
        id,
        name
      }
    }
    ```
    ```
    mutation {
      registBook(name: "人生論", authorId: "9c514822-5864-4a1d-8445-323fb0e15cd8") {
        id
        name
      }
    }
    ```
  
  * 注意点
    * schema.graphqlsにエラーがあるとエンドポイントが作成されないしログもでない...