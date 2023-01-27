import React from 'react'

export default function ProductList({fakeProducts}) {

    
  return (
    <>
    <div className='popular-products'>
    {fakeProducts.map(product=>(
        <> 
        <div className='product-overview' key={product.id}>
            <img src={product.image} alt={`image of ${product.title}`} />
            <h3>{product.title}</h3>
        </div>
        </>
       
      ))}
    </div>
    </>
  )
}
