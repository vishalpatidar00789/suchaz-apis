/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { CategoryImageSuchazComponent } from '../../../../../../main/webapp/app/entities/category-image-suchaz/category-image-suchaz.component';
import { CategoryImageSuchazService } from '../../../../../../main/webapp/app/entities/category-image-suchaz/category-image-suchaz.service';
import { CategoryImageSuchaz } from '../../../../../../main/webapp/app/entities/category-image-suchaz/category-image-suchaz.model';

describe('Component Tests', () => {

    describe('CategoryImageSuchaz Management Component', () => {
        let comp: CategoryImageSuchazComponent;
        let fixture: ComponentFixture<CategoryImageSuchazComponent>;
        let service: CategoryImageSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [CategoryImageSuchazComponent],
                providers: [
                    CategoryImageSuchazService
                ]
            })
            .overrideTemplate(CategoryImageSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CategoryImageSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CategoryImageSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new CategoryImageSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.categoryImages[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
