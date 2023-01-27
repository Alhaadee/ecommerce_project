import React from 'react'
import {useQuery, useMutation, useQueryClient} from '@tanstack/react-query'
import axios from 'axios';
import ProductList from '../components/ProductList';


function HomeContainer() {

  
 const queryClient = useQueryClient()
  
  const fetchProducts = () => {
    return axios.get('http://localhost:8080/api/v1/products')
  }

  const fetchFakeProducts = () => {
    return axios.get('https://fakestoreapi.com/products')
  }

  const {data: fakeProducts, isLoading, isError, error} = useQuery(["fake-products"], fetchFakeProducts)

  const products = useQuery(["products"], fetchProducts)

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
    <h2>Popular Items</h2>
    
    {isLoading ? <h1>Loading...</h1>:<ProductList fakeProducts={fakeProducts?.data.slice(0,4)}/>}
    
    </>
  )

  // maybe move the fetch request to the products list
}

export default HomeContainer