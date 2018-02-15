/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { CategorySuchazDetailComponent } from '../../../../../../main/webapp/app/entities/category-suchaz/category-suchaz-detail.component';
import { CategorySuchazService } from '../../../../../../main/webapp/app/entities/category-suchaz/category-suchaz.service';
import { CategorySuchaz } from '../../../../../../main/webapp/app/entities/category-suchaz/category-suchaz.model';

describe('Component Tests', () => {

    describe('CategorySuchaz Management Detail Component', () => {
        let comp: CategorySuchazDetailComponent;
        let fixture: ComponentFixture<CategorySuchazDetailComponent>;
        let service: CategorySuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [CategorySuchazDetailComponent],
                providers: [
                    CategorySuchazService
                ]
            })
            .overrideTemplate(CategorySuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CategorySuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CategorySuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new CategorySuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.category).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
