import {fireEvent, render} from "@testing-library/react";
import {AddAnimalForm} from "../index";

it("test add animal form inputs", () =>  {

    const {getByTestId} = render(<AddAnimalForm sizes={[]} breeds={[]}/>)
    const name = getByTestId("formName")
    const date = getByTestId("formDate")
    const description = getByTestId("formDescription")
    const size = getByTestId("formSize")
    const breed = getByTestId("formBreed")

    fireEvent.change(name, {target: {value: "Wacław"}})
    fireEvent.change(date, {target: {value: "2020-10-14"}})
    fireEvent.change(description, {target: {value: "Jakis opis"}})

    expect(name.value).toBe("Wacław")
    expect(date.value).toBe("2020-10-14")
    expect(description.value).toBe("Jakis opis")
    expect(size.value).toBe("")
    expect(breed.value).toBe("")
})



