/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { StoreImageSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/store-image-suchaz/store-image-suchaz-detail.component';
import { StoreImageSuchazService } from '../../../../../../main/webapp/app/entities/store-image-suchaz/store-image-suchaz.service';
import { StoreImageSuchaz } from '../../../../../../main/webapp/app/entities/store-image-suchaz/store-image-suchaz.model';

describe('Component Tests', () => {

    describe('StoreImageSuchaz Management Detail Component', () => {
        let comp: StoreImageSuchazDetailComponent;
        let fixture: ComponentFixture<StoreImageSuchazDetailComponent>;
        let service: StoreImageSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [StoreImageSuchazDetailComponent],
                providers: [
                    StoreImageSuchazService
                ]
            })
            .overrideTemplate(StoreImageSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(StoreImageSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StoreImageSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new StoreImageSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.storeImage).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
