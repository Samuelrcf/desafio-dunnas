<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Erro</title>
  <style>
    /* Reset básico */
    body, html {
      margin: 0;
      padding: 0;
      height: 100%;
      font-family: Arial, sans-serif;
      background-color: #f8d7da; /* vermelho claro para erro */
      color: #721c24; /* texto vermelho escuro */
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .container {
      background-color: #f5c6cb;
      border: 1px solid #f1b0b7;
      border-radius: 8px;
      padding: 30px 40px;
      max-width: 400px;
      text-align: center;
      box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    }

    h2 {
      margin-bottom: 20px;
      font-size: 1.6rem;
    }

    a {
      display: inline-block;
      padding: 10px 25px;
      background-color: #721c24;
      color: #fff;
      text-decoration: none;
      border-radius: 4px;
      font-weight: bold;
      transition: background-color 0.3s ease;
      box-shadow: 0 2px 5px rgba(114,28,36,0.5);
    }

    a:hover {
      background-color: #a94442;
      box-shadow: 0 4px 10px rgba(169,68,66,0.7);
    }
  </style>
</head>
<body>
  <div class="container">
    <h2>${errorMessage}</h2>
    <a href="/register">Tentar novamente</a>
  </div>
</body>
</html>
