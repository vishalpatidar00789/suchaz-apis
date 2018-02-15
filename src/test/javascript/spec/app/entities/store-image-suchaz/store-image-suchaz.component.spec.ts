/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { StoreImageSuchazComponent } from '../../../../../../main/webapp/app/entities/store-image-suchaz/store-image-suchaz.component';
import { StoreImageSuchazService } from '../../../../../../main/webapp/app/entities/store-image-suchaz/store-image-suchaz.service';
import { StoreImageSuchaz } from '../../../../../../main/webapp/app/entities/store-image-suchaz/store-image-suchaz.model';

describe('Component Tests', () => {

    describe('StoreImageSuchaz Management Component', () => {
        let comp: StoreImageSuchazComponent;
        let fixture: ComponentFixture<StoreImageSuchazComponent>;
        let service: StoreImageSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [StoreImageSuchazComponent],
                providers: [
                    StoreImageSuchazService
                ]
            })
            .overrideTemplate(StoreImageSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(StoreImageSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StoreImageSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new StoreImageSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.storeImages[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
