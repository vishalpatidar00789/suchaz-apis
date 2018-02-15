/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { OccasionImageSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/occasion-image-suchaz/occasion-image-suchaz-detail.component';
import { OccasionImageSuchazService } from '../../../../../../main/webapp/app/entities/occasion-image-suchaz/occasion-image-suchaz.service';
import { OccasionImageSuchaz } from '../../../../../../main/webapp/app/entities/occasion-image-suchaz/occasion-image-suchaz.model';

describe('Component Tests', () => {

    describe('OccasionImageSuchaz Management Detail Component', () => {
        let comp: OccasionImageSuchazDetailComponent;
        let fixture: ComponentFixture<OccasionImageSuchazDetailComponent>;
        let service: OccasionImageSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [OccasionImageSuchazDetailComponent],
                providers: [
                    OccasionImageSuchazService
                ]
            })
            .overrideTemplate(OccasionImageSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OccasionImageSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OccasionImageSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new OccasionImageSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.occasionImage).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
