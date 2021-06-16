import {render} from "@testing-library/react";
import {AnimalCard} from "../index";

it("test interests counter color for interested user", () =>  {
    render(<AnimalCard isInterested={true} animal={[]}/>)
    const container = document.getElementById("animalCardCounter");
    expect(container.className).toBe("interestCounter blueish");
})

it("test animal name paragraph", () =>  {
    const animal = {name: "Czarek"}
    render(<AnimalCard isInterested={true} animal={animal}/>)
    const container = document.getElementsByClassName("animalName");
    expect(container[0]).toHaveTextContent("Czarek");
})
