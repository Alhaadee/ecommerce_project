import React from 'react'
import {useMutation, } from '@tanstack/react-query'

const APIProducts = () => {


    const mutation = useMutation(product => {
        return axios.post('http://localhost:8080/api/v1/products', product)
      },{
        onSuccess: ()=>{queryClient.invalidateQueries("products")}
      })


      if(products.isError && isError){
        return <h1>Error: {products.error.message}</h1>
      }

  return (
    <>
    <ul>
    {products.data?.data.map(product=>(
      <li key={product.id}>{product.name}</li>
    ))}
  </ul>

  <button onClick={() => {mutation.mutate(
            {name: 'new product' ,
             description:"different discreption for the car",
             price:314.5,
             quantity:100}
             )
           }}
  > Create Todo
  </button>
  <button onClick={products.refetch}>fetch Products</button>
  </>
  )
}

export default APIProducts