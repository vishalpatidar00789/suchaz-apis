/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { TraitImageSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/trait-image-suchaz/trait-image-suchaz-detail.component';
import { TraitImageSuchazService } from '../../../../../../main/webapp/app/entities/trait-image-suchaz/trait-image-suchaz.service';
import { TraitImageSuchaz } from '../../../../../../main/webapp/app/entities/trait-image-suchaz/trait-image-suchaz.model';

describe('Component Tests', () => {

    describe('TraitImageSuchaz Management Detail Component', () => {
        let comp: TraitImageSuchazDetailComponent;
        let fixture: ComponentFixture<TraitImageSuchazDetailComponent>;
        let service: TraitImageSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [TraitImageSuchazDetailComponent],
                providers: [
                    TraitImageSuchazService
                ]
            })
            .overrideTemplate(TraitImageSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TraitImageSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TraitImageSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new TraitImageSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.traitImage).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
