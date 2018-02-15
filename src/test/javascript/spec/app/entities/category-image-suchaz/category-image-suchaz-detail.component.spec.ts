/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { CategoryImageSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/category-image-suchaz/category-image-suchaz-detail.component';
import { CategoryImageSuchazService } from '../../../../../../main/webapp/app/entities/category-image-suchaz/category-image-suchaz.service';
import { CategoryImageSuchaz } from '../../../../../../main/webapp/app/entities/category-image-suchaz/category-image-suchaz.model';

describe('Component Tests', () => {

    describe('CategoryImageSuchaz Management Detail Component', () => {
        let comp: CategoryImageSuchazDetailComponent;
        let fixture: ComponentFixture<CategoryImageSuchazDetailComponent>;
        let service: CategoryImageSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [CategoryImageSuchazDetailComponent],
                providers: [
                    CategoryImageSuchazService
                ]
            })
            .overrideTemplate(CategoryImageSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CategoryImageSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CategoryImageSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new CategoryImageSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.categoryImage).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
