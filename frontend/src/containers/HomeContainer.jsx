import React from 'react'
import {useQuery, useQueryClient} from '@tanstack/react-query'
import axios from 'axios';
import ProductList from '../components/ProductList';
import Hero from '../components/Hero';


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


  if(isError){
    return <h1>Error: {error.message}</h1>
  }

  
  return (
    <>
    <Hero />
    <h2>Popular Items</h2>
    
    {isLoading ? <h1>Loading...</h1>:<ProductList fakeProducts={fakeProducts?.data.slice(8,12)}/>}
    
    </>
  )

  // maybe move the fetch request to the products list
}

export default HomeContainer