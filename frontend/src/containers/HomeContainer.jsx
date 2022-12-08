import React from 'react'
import {useQuery, useMutation, useQueryClient} from 'react-query'
import axios from 'axios';

function HomeContainer() {

  
 const queryClient = useQueryClient()
  
  const fetchProducts = () => {
    return axios.get('http://localhost:8080/api/v1/products')
  }

  const products = useQuery("products", fetchProducts)

  const mutation = useMutation(product => {
    return axios.post('http://localhost:8080/api/v1/products', product)
  },{
    onSuccess: ()=>{queryClient.invalidateQueries("products")}
  })

  if(products.isLoading){
    return <h1>Loading...</h1>
  }
  if(products.isError){
    return <h1>Error: {products.error.message}</h1>
  }
  
  return (
    <>
    <h1>Home Container</h1>
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

export default HomeContainer