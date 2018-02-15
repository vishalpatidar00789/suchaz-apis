/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { CategorySuchazComponent } from '../../../../../../main/webapp/app/entities/category-suchaz/category-suchaz.component';
import { CategorySuchazService } from '../../../../../../main/webapp/app/entities/category-suchaz/category-suchaz.service';
import { CategorySuchaz } from '../../../../../../main/webapp/app/entities/category-suchaz/category-suchaz.model';

describe('Component Tests', () => {

    describe('CategorySuchaz Management Component', () => {
        let comp: CategorySuchazComponent;
        let fixture: ComponentFixture<CategorySuchazComponent>;
        let service: CategorySuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [CategorySuchazComponent],
                providers: [
                    CategorySuchazService
                ]
            })
            .overrideTemplate(CategorySuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CategorySuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CategorySuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new CategorySuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.categories[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
