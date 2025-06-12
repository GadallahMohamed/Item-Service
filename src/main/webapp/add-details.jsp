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
        <input type="text" required name=description value= "${itemDetailsData.description}">
        <div class="underline"></div>
        <label>Description</label>
      </div>
      
      <input type="hidden" required name="action" value="add-item-details">
    
      <input type="hidden" name="itemId" value="${param.id}">
        
       
     
    </div>
    <input type="submit" value="Add Details" class="button">
  </form>

  <p class="back">
    <a href="" >Back To Items</a>
  </p>
</div>
<!-- partial -->

</body>
</html>