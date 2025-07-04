<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>ADD Item</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
  <link rel="stylesheet" href="css/add-item.css">

</head>
<body>
<!-- partial:index.partial.html -->
<div class="container">
  <div class="text">
    Description
  </div>
  <form action="/item-service/ItemDetailsController">
    
    <div class="form-row">
      <div class="input-data">
        <input type="text" disabled="disabled" required name=description value= "${itemDetailsData.description}">
        <div class="underline"></div>
        
      </div>
      
      <input type="hidden" required name="action" value="add-item-details">
    
      <input type="hidden" name="itemId" value="${param.id}">
        
       
     
    </div>
    
  </form>

  <p class="back">
    <a href="itemController?action=load-items" >Back To Items</a>
  </p>
</div>
<!-- partial -->

</body>
</html>