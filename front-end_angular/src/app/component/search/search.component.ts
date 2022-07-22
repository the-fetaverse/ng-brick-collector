import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { LegoSet } from 'src/app/model/legoSet';
import { AjaxService } from 'src/app/service/ajax.service';
import { LegoSetService } from 'src/app/service/lego-set.service';
import { faSave } from '@fortawesome/free-solid-svg-icons';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
})
export class SearchComponent implements OnInit {
  searchDetail!: FormGroup;
  setObj: LegoSet = new LegoSet();
  setList: LegoSet[] = [];
  codeList: string[] = [];
  iconSave = faSave;
  isAlert: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private ajaxService: AjaxService,
    private legoService: LegoSetService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.searchDetail = this.formBuilder.group({
      id: [''],
      name: [''],
      year: [''],
      num_parts: [''],
    });
  }

  searchSet() {
    console.log(this.setObj.name);
    this.setObj.name = '';
    this.setObj.name = this.searchDetail.value.name;
    if (this.setObj.name.length === 0) {
      this.setObj.name = '';
      this.isAlert = true;
    }
    this.ajaxService.searchAPI(this.setObj).subscribe((res) => {
      if (res.count === 0) {
        this.setObj.name = '';
        this.isAlert = true;
      }
      res.results.map(
        (item: {
          name: string;
          year: number;
          set_img_url: string;
          num_parts: number;
        }) => {
          this.setObj.name = item.name;
          this.setObj.year = item.year;
          this.setObj.img = item.set_img_url;
          this.setObj.num_parts = item.num_parts;
        }
      );
    });
    this.searchDetail.reset();
  }

  addSet() {
    this.legoService.addSet(this.setObj).subscribe(
      (res) => {
        this.router.navigate(['/home']);
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
